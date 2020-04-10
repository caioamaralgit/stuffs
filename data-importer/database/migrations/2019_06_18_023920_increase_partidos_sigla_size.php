<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class IncreasePartidosSiglaSize extends Migration
{
    public function up()
    {
        Schema::table('partidos', function ($table) {
            $table->string('sigla', 50)->change();
        });
    }

    public function down()
    {
        Schema::table('partidos', function ($table) {
            $table->string('sigla', 8)->change();
        });
    }
}
