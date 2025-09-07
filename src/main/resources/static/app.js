// Variável global para armazenar o ID da denúncia em edição
let denunciaEmEdicaoId = null;

// Função para carregar e exibir as denúncias
async function carregarDenuncias() {
    const container = document.getElementById('denuncias-container');
    container.innerHTML = 'Carregando...';

    try {
        const response = await fetch('http://localhost:8081/api/denuncias');
        const denuncias = await response.json();

        container.innerHTML = ''; // Limpa a mensagem de carregamento

        if (denuncias.length === 0) {
            container.innerHTML = '<p>Nenhuma denúncia encontrada.</p>';
        } else {
            denuncias.forEach(denuncia => {
                const denunciaDiv = document.createElement('div');
                denunciaDiv.innerHTML = `
                    <h3>${denuncia.titulo}</h3>
                    <p>ID: ${denuncia.id}</p>
                    <p>Descrição: ${denuncia.descricao}</p>
                    <button onclick="preencherFormularioParaEdicao(${denuncia.id})">Editar</button>
                    <button onclick="excluirDenuncia(${denuncia.id})">Excluir</button>
                    <hr>
                `;
                container.appendChild(denunciaDiv);
            });
        }
    } catch (error) {
        console.error('Erro ao carregar denúncias:', error);
        container.innerHTML = '<p>Erro ao carregar as denúncias. Verifique se o servidor está rodando.</p>';
    }
}

// Função para enviar uma nova denúncia ou atualizar uma existente
async function enviarOuAtualizarDenuncia(event) {
    event.preventDefault();

    const form = document.getElementById('form-nova-denuncia');
    const denuncia = {
        titulo: form.titulo.value,
        descricao: form.descricao.value,
        nomeDoador: form.nomeDoador.value,
        contatoDoador: form.contatoDoador.value,
        anonima: false,
        tipo: form.tipo.value,
        classificacao: form.classificacao.value
    };

    let url = 'http://localhost:8081/api/denuncias';
    let method = 'POST';

    // Se estiver em modo de edição, muda para o método PUT
    if (denunciaEmEdicaoId) {
        url = `${url}/${denunciaEmEdicaoId}`;
        method = 'PUT';
    }

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(denuncia)
        });

        if (response.ok) { // Status 200 para PUT, 201 para POST
            alert(`Denúncia ${denunciaEmEdicaoId ? 'atualizada' : 'criada'} com sucesso!`);
            form.reset();
            denunciaEmEdicaoId = null; // Reseta o modo de edição
            document.getElementById('form-button').textContent = 'Enviar Denúncia'; // Volta o texto do botão
            carregarDenuncias();
        } else {
            alert('Erro ao processar denúncia. Verifique se os campos estão corretos.');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao enviar a denúncia. Verifique a conexão com o servidor.');
    }
}


// --- CÓDIGO PARA EDIÇÃO ---

// Função para buscar os dados de uma denúncia e preencher o formulário
async function preencherFormularioParaEdicao(id) {
    try {
        const response = await fetch(`http://localhost:8081/api/denuncias/${id}`);
        if (!response.ok) {
            throw new Error('Denúncia não encontrada');
        }
        const denuncia = await response.json();

        // Preenche os campos do formulário
        document.getElementById('titulo').value = denuncia.titulo;
        document.getElementById('descricao').value = denuncia.descricao;
        document.getElementById('nomeDoador').value = denuncia.nomeDoador || '';
        document.getElementById('contatoDoador').value = denuncia.contatoDoador || '';
        document.getElementById('tipo').value = denuncia.tipo;
        document.getElementById('classificacao').value = denuncia.classificacao;

        // Armazena o ID da denúncia para o modo de edição
        denunciaEmEdicaoId = id;
        document.getElementById('form-button').textContent = 'Atualizar Denúncia';

        // Rolamos a página para o formulário
        document.getElementById('form-nova-denuncia').scrollIntoView({ behavior: 'smooth' });

    } catch (error) {
        console.error('Erro ao buscar denúncia para edição:', error);
        alert('Erro ao carregar os dados da denúncia para edição.');
    }
}


// --- CÓDIGO PARA DELETAR ---

// Função para excluir uma denúncia
async function excluirDenuncia(id) {
    if (!confirm('Tem certeza que deseja excluir esta denúncia?')) {
        return;
    }

    try {
        const response = await fetch(`http://localhost:8081/api/denuncias/${id}`, {
            method: 'DELETE'
        });

        if (response.status === 204) {
            alert('Denúncia excluída com sucesso!');
            carregarDenuncias();
        } else if (response.status === 404) {
            alert('Denúncia não encontrada.');
        } else {
            alert('Erro ao excluir denúncia.');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao tentar excluir a denúncia. Verifique a conexão com o servidor.');
    }
}


// Chame a função para carregar as denúncias quando a página carregar
window.onload = carregarDenuncias;

// Adicione o ouvinte de evento para o formulário
document.getElementById('form-nova-denuncia').addEventListener('submit', enviarOuAtualizarDenuncia);