● Plano de Projeto Frontend - Sistema Checklist                                           
                                                                                          
  1. Visão Geral do Backend                                                                                                                                                     
                                                                                                                                                                                
  O backend é uma API REST em Java 17 + Spring Boot 3+ com:                                                                                                                     
  - Banco: MySQL (checklist_db)                                                                                                                                                 
  - Entidades: ProjetoModel (1) → AmbienteModel (N) com 15+ enums para tipos de acabamentos
  - Endpoints: POST/GET/DELETE /api/projetos e GET /api/projetos/{id}
  - CORS: Habilitado para allowedOrigins("*")
  2. Arquitetura Frontend Recomendada

  ┌──────────────────┬────────────────────────────┬──────────────────────────────────────────────────────────────────────────────────────┐
  │      Camada      │         Tecnologia         │                                    Justificativa                                     │
  ├──────────────────┼────────────────────────────┼──────────────────────────────────────────────────────────────────────────────────────┤
  │ Framework        │ Vue.js 3 (Composition API) │ Integração reativa perfeita com APIs REST, curva de aprendizado suave, bundle enxuto │
  ├──────────────────┼────────────────────────────┼──────────────────────────────────────────────────────────────────────────────────────┤
  │ Build Tool       │ Vite                       │ Hot reload instantâneo, build otimizado, configuração zero                           │
  ├──────────────────┼────────────────────────────┼──────────────────────────────────────────────────────────────────────────────────────┤
  │ Estilização      │ Tailwind CSS + DaisyUI     │ Componentes prontos, tema consistente, produtividade alta                            │
  ├──────────────────┼────────────────────────────┼──────────────────────────────────────────────────────────────────────────────────────┤
  │ HTTP Client      │ Axios                      │ Interceptors para tratamento global de erros, baseURL configurável                   │
  ├──────────────────┼────────────────────────────┼──────────────────────────────────────────────────────────────────────────────────────┤
  │ Router           │ Vue Router 4               │ SPA com navegação entre "Cadastrar" e "Listar"                                       │
  ├──────────────────┼────────────────────────────┼──────────────────────────────────────────────────────────────────────────────────────┤
  │ State Management │ Pinia                      │ Alternativa moderna ao Vuex, API mais simples                                        │
  └──────────────────┴────────────────────────────┴──────────────────────────────────────────────────────────────────────────────────────┘

  3. Estrutura de Pastas

  checklist-frontend/
  ├── src/
  │   ├── api/           # Chamadas axios (projetoService.js)
  │   ├── assets/        # Ícones, logos
  │   ├── components/    # Componentes reutilizáveis
  │   │   ├── FormProjeto.vue
  │   │   ├── FormAmbiente.vue
  │   │   └── ItemLista.vue
  │   ├── router/        # Rotas da aplicação
  │   ├── stores/        # Pinia stores
  │   ├── App.vue
  │   └── main.js
  ├── index.html
  └── vite.config.js

  4. Principais Funcionalidades

  Módulo Projeto:
  - Listar projetos (nome cliente, vendedor, data)
  - Criar projeto com ambientes aninhados
  - Visualizar detalhes do projeto
  - Deletar projeto

  Módulo Ambiente:
  - Formulário dinâmico com select de enums (15 opções por ambiente)
  - Adição/remover múltiplos ambientes por projeto

  Funcionalidade Impressão:
  - CSS @media print para "Detalhamento Técnico"
  - Modal/PDF formatado com dados do projeto

  5. Integração API

  // Base URL: http://localhost:8080/api/projetos
  POST   /api/projetos      → Criar (corpo: ProjetoModel + ambientes[])
  GET    /api/projetos      → Listar todos
  GET    /api/projetos/{id} → Detalhes
  DELETE /api/projetos/{id} → Deletar

  6. Configurações Técnicas

  Vite Proxy (vite.config.js):
  server: {
    proxy: {
      '/api': { target: 'http://localhost:8080', changeOrigin: true }
    }
  }

  Axios Instance:
  const api = axios.create({
    baseURL: '/api',
    headers: { 'Content-Type': 'application/json' }
  })

  7. Roteiro de Implementação

  1. Setup Inicial: Vite + Vue 3 + Tailwind + Router + Pinia
  2. Tela Listagem: GET /projetos → tabela com cards
  3. Tela Cadastro: Formulário Projeto + Ambiente com selects dinâmicos
  4. Integração: Conectar formulários à API
  5. Estilização: Tema clean, responsivo
  6. Print View: CSS impressão com detalhamento técnico
  7. Testes: Validação cruzada com backend

  Justificativa das Escolhas:
  - Vue.js tem menor curva que React/Angular, ideal para time pequeno
  - Tailwind + DaisyUI acelera desenvolvimento sem sacrificar customização
  - Pinia + Axios simplificam state management e comunicação HTTP
  - Vite reduz tempo de feedback durante desenvolvimento