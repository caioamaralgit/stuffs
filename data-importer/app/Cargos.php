<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Cargos extends Model
{
    public $timestamps = false;
    protected $table = "cargos";
    protected $fillable = ["cargo"];

	public function votos()
	{
		return $this->hasMany(Votos::class, 'cargos_id', 'id');
	}
}
