<?php

use App\Http\Controllers\PersonController;
use Illuminate\Support\Facades\Route;

Route::prefix('person')->group(function () {
    Route::get('/{id}', [PersonController::class, 'getById']);
    Route::post('/change-avatar/{id}', [PersonController::class, 'changeAvatar']);
});
