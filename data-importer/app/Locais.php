<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Locais extends Model
{
    public $timestamps = false;
    protected $table = "locais";
    protected $fillable = ["cidade", "estado"];
    
	public function votos()
	{
		return $this->hasMany(Votos::class, 'locais_id', 'id');
	}
}
