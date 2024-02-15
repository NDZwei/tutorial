<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Storage;

class Person extends Model
{
    use HasFactory;

    protected $fillable = [
        'display_name',
        'phone_number',
        'address',
        'avatar',
    ];

    protected $casts = [
        'birthdate' => 'datetime',
    ];

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function getAvatarAttribute($value)
    {
        if ($value) {
            $urlSlashToDot = str_replace("/", ".", $value);
            $avatarUrl = 'images/persons/' .$this->getKey().'/';
            $avatarUrl = $avatarUrl . $urlSlashToDot;
            if (Storage::disk('public')->exists($avatarUrl)) {
                return url('storage/' . $avatarUrl);
            }
        }
        return null;
    }
}
