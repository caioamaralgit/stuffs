<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Pessoas extends Model
{
    public $timestamps = false;
    protected $table = "pessoas";
    protected $fillable = ["nome", "sigla", "cargos_id", "candidatos_id", "partidos_id"];
    
	public function cargo()
	{
		return $this->hasOne(Cargos::class, 'cargos_id');
	}
    
	public function candidato()
	{
		return $this->hasOne(Candidatos::class, 'candidatos_id');
	}
    
	public function partido()
	{
		return $this->hasOne(Partidos::class, 'partidos_id');
	}
}
