<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Tempo extends Model
{
    public $timestamps = false;
    protected $table = "tempo";
    protected $fillable = ["ano", "semestre"];
    
	public function votos()
	{
		return $this->hasMany(Votos::class, 'tempo_id', 'id');
	}
}
