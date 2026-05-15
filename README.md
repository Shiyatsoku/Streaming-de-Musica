# 🎵 Sistema de Streaming de Música

## 📋 Funcionalidades

- Cadastro de usuários Free e Premium
- Sistema de login por e-mail
- Cadastro de músicas
- Listagem de músicas cadastradas
- Busca de músicas por título
- Reprodução de músicas
- Reprodução de playlists
- Criação de playlists personalizadas
- Playlists automáticas:
  - Top 10 Mais Tocadas
  - Recomendadas para Você
  - Adicionadas Recentemente
- Sistema de anúncios para usuários Free
- Limite de 30 reproduções para usuários Free
- Sistema de upgrade para Premium
- Download de músicas para usuários Premium
- Estatísticas do sistema:
  - Quantidade de usuários
  - Reproduções totais
  - Anúncios exibidos
- Interface visual no terminal com cores e menus estilizados

---

## 🏗️ Arquitetura

O projeto foi feito utilizando conceitos de Programação Orientada a Objetos (POO).

### Pacotes

#### `br.com.streaming.modelo`
Contém as classes principais do sistema:
- `Usuario`
- `UsuarioFree`
- `UsuarioPremium`
- `Musica`
- `Playlist`
- `PlaylistAutomatica`
- `PlaylistPersonalizada`

#### `br.com.streaming.principal`
Contém a classe principal:
- `StreamingMusica`

Responsável pelos menus, interação com usuário e execução do sistema.

#### `br.com.streaming.util`
Classes auxiliares:
- `Cores`
- `FormatadorTempo`
- `Validador`

#### `br.com.streaming.servico`
Interfaces e serviços:
- `Baixavel`
- `Reproduzivel`
- `GeradorRecomendacoes`

---

## 🚀 Como Executar
Antes de começar, você precisa ter instalado:

- Java JDK 17+ (ou a versão compatível com seu projeto)
- VS Code, IntelliJ IDEA ou Eclipse
- Git (opcional)

---

👤 Autor
Nome: Miguel Augusto da Costa Souza
RA: 43998704

---

📅 Histórico

Checkpoint 1
Criação da estrutura inicial do projeto
Cadastro de músicas

Checkpoint 2
Sistema de usuários Free e Premium
Login de usuários

Checkpoint 3
Criação e gerenciamento de playlists
Reprodução de músicas

Checkpoint 4
Implementação de playlists automáticas
Interface visual no terminal

Checkpoint 5
Estatísticas do sistema
Sistema de anúncios e limite para usuários Free

Checkpoint 6
Correções de bugs
Melhorias visuais
Download de músicas Premium
Ajustes finais do sistema
