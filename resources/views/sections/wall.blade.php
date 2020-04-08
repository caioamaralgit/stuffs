<section class="section" id="wall">
    <h2>Mural</h2>
    <div>
        @foreach ($messages as $message)
        <div class="d-flex flex-row message my-3">
            <div class="date mr-3">
                {{ $message->getSentDate() }}
            </div>
            <div class="content">                
                <strong>{{ $message->name }}</strong>
                <p>{{ $message->message }}</p>
            </div>
        </div>
        @endforeach
        @if (count($messages) === 0)
            <p class="h5 my-2 text-center">Ninguém escreveu ainda. Deixe a primeira mensagem!</p>
        @endif
        <hr />
        <p class="text-center">Deixe sua mensagem pelo formulário abaixo. Vamos ler e colocar no mural!</p>
        <form action="/" method="POST">
            @csrf
            <div class="form-group">
                <label for="form-name">Nome:</label>
                <input type="text" class="form-control" id="form-name" name="name" placeholder="Seu nome" required />
            </div>
            <div class="form-group">
                <label for="form-email">Email:</label>
                <input type="email" class="form-control" id="form-email" name="email" placeholder="Nos diga seu email" required />
            </div>
            <div class="form-group">
                <label for="form-message">Sua mensagem:</label>
                <textarea class="form-control" id="form-message" name="message" rows="3" placeholder="Coloque aqui sua mensagem" required></textarea>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Enviar mensagem</button>
            </div>
        </form>
    </div>
</section>
@if (Session::has("success"))
@if (session("success"))
<div class="alert alert-success" role="alert">
  Sua mensagem foi registrada! Em breve estará disponível.
</div>
@else
<div class="alert alert-danger" role="alert">
    Ocorreu um problema ao registrar sua mensagem... 
    Tente novamente mais tarde ou <a href="mailto:caio.amaral.lds@gmail.com">nos envie um email</a>.
</div>
@endif
<script>
    setTimeout(function () {
        $(".alert").fadeOut();
    }, 10000);
</script>
@endif