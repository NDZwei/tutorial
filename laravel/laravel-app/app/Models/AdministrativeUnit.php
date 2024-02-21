<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class AdministrativeUnit extends Model
{
    use HasFactory;

    protected $fillable = ['code', 'name', 'level', 'parent_id'];

    public function children()
    {
        return $this->hasMany(AdministrativeUnit::class);
    }

    public function parent()
    {
        return $this->belongsTo(AdministrativeUnit::class);
    }
}
