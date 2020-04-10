<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePartidosTable extends Migration
{
    public function up()
    {
        Schema::create('partidos', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->string('sigla', '8');
            $table->string('partido', '80');
        });
    }

    public function down()
    {
        Schema::dropIfExists('partidos');
    }
}
