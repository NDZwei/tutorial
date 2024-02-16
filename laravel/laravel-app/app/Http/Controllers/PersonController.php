<?php

namespace App\Http\Controllers;

use App\Services\PersonService;
use Exception;
use Illuminate\Http\Request;

class PersonController extends BaseController {
    public function __construct(PersonService $service)
    {
        parent::__construct($service);
    }

    public function changeAvatar(int $id, Request $request) {
        try {
            $result = $this->service->changeAvatar($id, $request);
            if($result === true) {
                return $this->getResponse200();
            }
            return $this->getResponse500();
        } catch (Exception $e) {
            return $this->getResponse500($e->getMessage());
        }
    }
}
