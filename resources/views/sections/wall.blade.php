<section class="section" id="wall">
    <h2>Mural</h2>
    <div>
        <div class="d-flex flex-row testimony">
            <div class="mr-3">
                13 <br />
                JUL
            </div>
            <div>                
                <strong>Caio Amaral</strong>
                <p>
                    Felicidades!!! <br />
                    Felicidades!!! <br />
                    Felicidades!!! <br />
                    Felicidades!!! <br />
                </p>
            </div>
        </div>

        <hr />
        <p class="text-center">Deixe sua mensagem pelo formulário abaixo. Vamos ler e colocar no mural!</p>
        <form action="/" method="POST">
            @csrf
            <div class="form-group">
                <label for="form-name">Nome:</label>
                <input type="text" class="form-control" id="form-name" placeholder="Seu nome" required />
            </div>
            <div class="form-group">
                <label for="form-email">Email:</label>
                <input type="email" class="form-control" id="form-email" placeholder="Nos diga seu email" required />
            </div>
            <div class="form-group">
                <label for="form-message">Sua mensagem:</label>
                <textarea class="form-control" id="form-message" rows="3" placeholder="Coloque aqui sua mensagem" required></textarea>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Enviar mensagem</button>
            </div>
        </form>
    </div>
</section>