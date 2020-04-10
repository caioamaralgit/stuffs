<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Partidos extends Model
{
    public $timestamps = false;
    protected $table = "partidos";
    protected $fillable = ["partido", "sigla"];
    
	public function votos()
	{
		return $this->hasMany(Votos::class, 'partidos_id', 'id');
	}
}
