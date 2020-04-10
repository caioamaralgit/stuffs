<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateLocaisTable extends Migration
{
    public function up()
    {
        Schema::create('locais', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->string('cidade', '140');
            $table->char('estado', '2');
        });
    }

    public function down()
    {
        Schema::dropIfExists('locais');
    }
}
