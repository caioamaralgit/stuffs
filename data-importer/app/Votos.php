<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Votos extends Model
{
    public $timestamps = false;
    protected $table = "candidatos";
    protected $fillable = ["cargos_id", "candidatos_id", "partidos_id", "locais_id", "tempo_id", "quantidade"];   

	public function cargo()
	{
		return $this->belongsTo(Cargos::class, 'cargos_id');
	}

	public function tempo()
	{
		return $this->belongsTo(Tempo::class, 'tempo_id');
	}

	public function local()
	{
		return $this->belongsTo(Locais::class, 'locais_id');
	}

	public function candidato()
	{
		return $this->belongsTo(Candidatos::class, 'candidatos_id');
	}

	public function partido()
	{
		return $this->belongsTo(Partidos::class, 'partidos_id');
	}
}
