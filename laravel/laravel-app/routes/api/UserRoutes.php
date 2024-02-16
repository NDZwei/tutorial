<?php

use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

Route::prefix('user')->group(function () {
    Route::get('/all', [UserController::class, 'getAll']);
    Route::get('/{id}', [UserController::class, 'getById']);
    Route::post('/save', [UserController::class, 'save']);
    Route::delete('/{id}', [UserController::class, 'deleteById']);
    Route::post('/get-with-relation', [UserController::class, 'getDataColumnWithRelation']);
    Route::post('/get-by-ids', [UserController::class, 'findByIds']);
    Route::post('/get-by-columns', [UserController::class, 'findByColumns']);
    Route::post('/get-page', [UserController::class, 'findByColumns']);

});
