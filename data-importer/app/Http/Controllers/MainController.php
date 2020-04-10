<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Candidatos;
use App\Cargos;
use App\Locais;
use App\Partidos;
use App\Pessoas;
use App\Tempo;
use App\Votos;

class MainController extends Controller
{
    public function importarArquivoCandidatos(Request $request)
    {
        $reader = \PhpOffice\PhpSpreadsheet\IOFactory::createReader("Xlsx");
        $reader->setReadDataOnly(true);
        
        $spreadsheet = $reader->load($request->candidatos->getPathName());
        $worksheet = $spreadsheet->getActiveSheet();
        $candidatos = $worksheet->toArray();

        foreach ($candidatos as $index => $candidato) 
        {
            $pessoa = new Pessoas();
            $pessoa->nome = $candidato[2];
            $pessoa->sigla = $candidato[3];
            $pessoa->candidatos_id = $this->adicionarCandidato($candidato[6], $candidato[5])->id;
            $pessoa->cargos_id = $this->adicionarCargo($candidato[0])->id;
            $pessoa->partidos_id = $this->adicionarPartido($candidato[4], $candidato[3])->id;
            $pessoa->save();
        }
        
        $novaPlanilha = $reader->load($request->votos->getPathName());
        $tabela = $novaPlanilha->getActiveSheet();
        $votos = $tabela->toArray();
        
        foreach ($votos as $index => $voto) 
        {
            $pessoa = Pessoas::where([["nome", $voto[6]], ["sigla", $voto[8]]])->first();

            if ($pessoa !== null) {
                $locais_id = $this->adicionarLocal($voto[4], $voto[3])->id;
                $tempo_id = $this->adicionarTempo($voto[0])->id;
                
                $this->adicionarVoto($voto[8], $pessoa->cargos_id, $pessoa->candidatos_id, $pessoa->partidos_id, $locais_id, $tempo_id);
            }
        }

        return redirect()->back();
    }

    public function adicionarCandidato($profissao, $escolaridade)
    {
        return Candidatos::firstOrCreate([
            "profissao" => $profissao,
            "escolaridade" => $escolaridade
        ]);
        
    }

    public function adicionarCargo($cargo) 
    {
        return Cargos::firstOrCreate([
            "cargo" => $cargo
        ]);
    }

    public function adicionarLocal($cidade, $estado)
    {
        return Locais::firstOrCreate([
            "estado" => $estado,
            "cidade" => $cidade
        ]);
    }

    public function adicionarPartido($partido, $sigla)
    {
        return Partidos::firstOrCreate([
            "partido" => $partido,
            "sigla" => $sigla
        ]);
    }

    public function adicionarTempo($ano)
    {
        return Tempo::firstOrCreate([
            "ano" => $ano,
            "semestre" => 2
        ]);
    }

    public function adicionarVoto($quantidade, $cargos_id, $candidatos_id, $partidos_id, $locais_id, $tempo_id) 
    {
        $registro = Votos::create([
            "quantidade" => $quantidade,
            "cargos_id" => $cargos_id,
            "candidatos_id" => $candidatos_id,
            "partidos_id" => $partidos_id,
            "locais_id" => $locais_id,
            "tempo_id" => $tempo_id
        ]);

        $registro->save();
    }
}
