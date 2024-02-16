<?php

namespace App\Services;

use App\Repositories\PersonRepository;
use App\Repositories\UserRepository;
use Exception;
use Illuminate\Support\Facades\DB;

class UserService extends BaseService {
    protected $personRepository;

    public function __construct(UserRepository $repository, PersonRepository $personRepository)
    {
        parent::__construct($repository);
        $this->personRepository = $personRepository;
    }

    public function save(array $data)
    {
        try {
            DB::beginTransaction();
            $user = $this->repository->save($data);
            $person['user_id'] = $user->id;
            $this->personRepository->save($person);
            DB::commit();
            return $user;
        } catch (Exception $e) {
            DB::rollBack();
            return $e->getMessage();
        }
    }
}
