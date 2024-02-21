<?php

namespace App\Http\Controllers;

use App\Exports\AdministrativeTemplate;
use App\Imports\AdministrativeUnitImport;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Log;
use Maatwebsite\Excel\Facades\Excel;

class ExcelController extends Controller {

    public function downloadAdministrativeUnitTemplate() {
        $data = [
            ['code', 'name', 'level']
        ];
        return Excel::download(new AdministrativeTemplate($data), 'administrative_unit_template.xlsx');
    }

    public function importAdministrativeUnit(Request $request) {
        $request->validate([
            'template' => 'required|mimes:xlsx',
        ]);
        $file = $request->file('template');
        Excel::import(new AdministrativeUnitImport, $file);
            $response = [
                'status' => Response::HTTP_OK,
                'message' => 'Success',
            ];
            return response()->json($response);
    }
}
