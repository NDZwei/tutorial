<?php

namespace App\Repositories;

use App\Models\Person;

class PersonRepository extends BaseRepository {
    function getModel() {
        return Person::class;
    }
}
