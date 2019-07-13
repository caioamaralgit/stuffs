<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <title>My Wedding • Julia & Caio</title>

        <meta charset="UTF-8" />
        <meta name="csrf-token" content="{{ csrf_token() }}" />
        <meta name="theme-color" content="#ffffff" />
        <meta name="msapplication-TileColor" content="#2b5797" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
        <link rel="apple-touch-icon" sizes="180x180" href="{{ asset('img/icon/apple-touch-icon.png') }}">
        <link rel="icon" type="image/png" sizes="32x32" href="{{ asset('img/icon/favicon-32x32.png') }}">
        <link rel="icon" type="image/png" sizes="16x16" href="{{ asset('img/icon/favicon-16x16.png') }}">
        <link rel="icon" type="image/x-icon" href="{{ asset('favicon.ico') }}" />
        <link rel="manifest" href="{{ asset('site.webmanifest') }}">

        @include("snippets.fonts")
        
        <link href="{{ asset('css/app.css') }}" rel="stylesheet" />
        <link href="{{ asset('css/countdown.css') }}" rel="stylesheet" />
        <script src="{{ asset('js/app.js') }}" type="text/javascript"></script>
    </head>
    <body>
        @include("partials.navbar")
        @include("sections.header")        
        @include("sections.countdown")        
        @include("sections.history")        
        @include("sections.gallery")        
        @include("sections.wall")        
    </body>
</html>