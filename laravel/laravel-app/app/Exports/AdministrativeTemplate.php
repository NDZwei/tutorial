<?php

namespace App\Exports;

use Maatwebsite\Excel\Concerns\FromArray;
use Maatwebsite\Excel\Concerns\WithStyles;
use PhpOffice\PhpSpreadsheet\Worksheet\Worksheet;

class AdministrativeTemplate implements FromArray, WithStyles
{
    protected $data;

    public function __construct(array $data)
    {
        $this->data = $data;
    }

    public function array(): array
    {
        return $this->data;
    }

    public function styles(Worksheet $sheet)
    {
        $lastRow = $sheet->getHighestRow();
        $styleArray = [
            'font' => [
                'name' => 'Times New Roman',
                'size' => 13,
            ],
        ];

        $sheet->getStyle("1:{$lastRow}")->applyFromArray($styleArray);

    }
}
