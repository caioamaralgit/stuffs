<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateVotosTable extends Migration
{
    public function up()
    {
        Schema::create('votos', function (Blueprint $table) {
            $table->unsignedInteger('cargos_id');
            $table->unsignedInteger('profissoes_id');
            $table->unsignedInteger('escolaridade_id');
            $table->unsignedInteger('partidos_id');
            $table->unsignedInteger('locais_id');
            $table->unsignedInteger('tempo_id');
            $table->integer('quantidade');
        });
    }

    public function down()
    {
        Schema::dropIfExists('votos');
    }
}
