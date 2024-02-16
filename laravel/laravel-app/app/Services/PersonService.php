<?php

namespace App\Services;

use App\Repositories\PersonRepository;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class PersonService extends BaseService {

    public function __construct(PersonRepository $repository)
    {
        parent::__construct($repository);
    }

    public function changeAvatar(int $id, Request $request) {
        $person = $this->repository->getById($id);
        if($person != null && $request->hasFile('avatar')) {
            $avatar = $request->file('avatar');
            $imagePath = 'images/persons/'.$id;
            if ($person->avatar) {
                $previousAvatar = basename($person->avatar);
                Storage::disk('public')->delete($imagePath.'/'.$previousAvatar);
            }
            $extension = $avatar->getClientOriginalExtension(); // .png, .jpeg...
            $avatarName = strtolower(preg_replace('/[^a-zA-Z0-9_]/', '_', pathinfo($avatar->getClientOriginalName(), PATHINFO_FILENAME)));
            $avatarName = $avatarName . '_' . time() . '.' .$extension;
            $avatar->storeAs($imagePath, $avatarName, 'public');
            $person->update(['avatar' => $avatarName]);
            return true;
        }
        return false;
    }
}
