<?php

namespace App\Services;

use App\Repositories\AdministrativeRepository;

class AdministrativeService extends BaseService {

    public function __construct(AdministrativeRepository $repository)
    {
        parent::__construct($repository);
    }
}
