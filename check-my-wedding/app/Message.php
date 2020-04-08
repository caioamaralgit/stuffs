<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Message extends Model
{
    public $timestamps = false;
    protected $table = "messages";

    protected $fillable = ["name", "email", "message", "sent_at"];
    protected $dates = [
        'sent_at',
    ];
    
    public function getSentDate()
    {
        $sent_at = explode('-', explode(' ', $this->sent_at)[0]);
        $date = $sent_at[2];

        switch ($sent_at[1])
        {
            case "01": 
                $date .= " JAN";
                break;
            case "02": 
                $date .= " FEV";
                break;
            case "03": 
                $date .= " MAR";
                break;
            case "04": 
                $date .= " ABR";
                break;
            case "05": 
                $date .= " MAI";
                break;
            case "06": 
                $date .= " JUN";
                break;
            case "07": 
                $date .= " JUL";
                break;
            case "08": 
                $date .= " AGO";
                break;
            case "09": 
                $date .= " SET";
                break;
            case "10": 
                $date .= " OUT";
                break;
            case "11": 
                $date .= " NOV";
                break;
            case "12": 
                $date .= " DEZ";
                break;
            default:
                break;
        }   

        return $date;
    }
}
