<?php

namespace App\Repositories;

use App\Models\AdministrativeUnit;

class AdministrativeRepository extends BaseRepository {

    function getModel() {
        return AdministrativeUnit::class;
    }
}
