<?php

namespace App\Imports;

use App\Models\AdministrativeUnit;
use Maatwebsite\Excel\Concerns\ToModel;
use Maatwebsite\Excel\Concerns\WithHeadingRow;
use Maatwebsite\Excel\Concerns\WithStartRow;

class AdministrativeUnitImport implements ToModel, WithHeadingRow
{
    public function model(array $row)
    {
        return new AdministrativeUnit([
            'code' => $row['code'],
            'name' => $row['name'],
            'level' => $row['level']
        ]);
    }
}
    // public function startRow(): int
    // {
    //     return 1;
    // }
    // WithHeadingRow để biết import có heading
