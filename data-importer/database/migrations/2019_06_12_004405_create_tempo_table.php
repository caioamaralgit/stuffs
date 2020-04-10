<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateTempoTable extends Migration
{
    public function up()
    {
        Schema::create('tempo', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->integer('ano');
            $table->integer('semestre');
        });
    }

    public function down()
    {
        Schema::dropIfExists('tempo');
    }
}
