<?php

use App\Http\Controllers\ExcelController;
use Illuminate\Support\Facades\Route;

Route::prefix('excel')->group(function () {
    Route::get('/download-administrative-unit-template', [ExcelController::class, 'downloadAdministrativeUnitTemplate']);
    Route::post('/import-administrative-unit', [ExcelController::class, 'importAdministrativeUnit']);
});
