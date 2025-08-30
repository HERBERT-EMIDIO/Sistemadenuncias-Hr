const denuncia = {
        titulo: form.titulo.value,
        descricao: form.descricao.value,
        nomeDoador: form.nomeDoador.value,
        // Campos que precisam ser ajustados:
        anonima: false,
        tipo: "EVENTO ADVERSO",
        classificacao: "LEVE"
    };// Função para carregar e exibir as denúncias
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

// Função para enviar uma nova denúncia
async function enviarDenuncia(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

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

    try {
        const response = await fetch('http://localhost:8081/api/denuncias', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(denuncia)
        });

        if (response.status === 201) {
            alert('Denúncia criada com sucesso!');
            form.reset(); // Limpa o formulário
            carregarDenuncias(); // Recarrega a lista de denúncias
        } else {
            alert('Erro ao criar denúncia. Verifique se todos os campos estão corretos.');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao enviar a denúncia. Verifique a conexão com o servidor.');
    }
}


// Chame a função para carregar as denúncias quando a página carregar
window.onload = carregarDenuncias;

// Adicione o ouvinte de evento para o formulário
document.getElementById('form-nova-denuncia').addEventListener('submit', enviarDenuncia);