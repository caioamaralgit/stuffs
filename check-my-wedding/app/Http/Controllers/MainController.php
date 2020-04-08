<?php

namespace App\Http\Controllers;

use App\Message;
use Illuminate\Http\Request;

class MainController extends Controller
{
    public function index()
    {
        $messages = Message::where("display", 1)->get();

        return view('app', compact("messages"));
    }

    public function sendMessage(Request $request) 
    {
        Message::create([
            "name" => $request->name,
            "email" => $request->email,
            "message" => $request->message
        ]);

        return redirect()->back()->with("success", true);
    }
}
