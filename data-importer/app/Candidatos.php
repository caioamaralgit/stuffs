<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Candidatos extends Model
{
    public $timestamps = false;
    protected $table = "candidatos";
    protected $fillable = ["profissao", "escolaridade"];
    
	public function votos()
	{
		return $this->hasMany(Votos::class, 'candidatos_id', 'id');
	}
}
