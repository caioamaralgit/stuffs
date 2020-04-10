<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePessoaTable extends Migration
{
    public function up()
    {
        Schema::create('pessoas', function (Blueprint $table) {
            $table->string('nome', '300');
            $table->string('sigla', '50');
            $table->unsignedInteger('cargos_id');
            $table->unsignedInteger('candidatos_id');
            $table->unsignedInteger('partidos_id');
        });
    }

    public function down()
    {
        Schema::dropIfExists('pessoas');
    }
}
