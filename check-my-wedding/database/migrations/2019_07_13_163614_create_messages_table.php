<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateMessagesTable extends Migration
{
    public function up()
    {
        Schema::create('messages', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->timestamp('sent_at')->useCurrent();
            $table->string('name');
            $table->string('email');
            $table->string('message');
            $table->boolean('display')->default(false);
        });
    }

    public function down()
    {
        Schema::dropIfExists('messages');
    }
}
