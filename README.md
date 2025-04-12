# LEDA_SteamProject
Primeira parte do projeto da cadeira de Laboratório de Estruturas de Dados


### **Observações iniciais**

O Banco de Dados do Kaggle disponibilizado para usarmos no nosso projeto tinha um problema no cabeçalho, onde a coluna `DiscountDLC count` está errada. A coluna única deveria estar dividida em duas, que seriam `Discount`, `DLC count`.

Devido a isso, quando o programa rodava e procurava os índices das colunas após a `DiscountDLC count`, ele sempre retornava um valor errado, prejudicando a execução do projeto e o retorno esperado.

Por isso, para não modificar o banco de dados diretamente e correr risco de perder as informações durante a execução do programa, decidimos que ao invés de utilizar o arquivo `games.csv` para gerar o arquivo `games_formated_release_data.csv`, nós iriamos criar um arquivo temporário, onde o programa iria modificar o cabeçalho da forma correta e salvar o resultado nesse arquivo, e a partir desse arquivo temporário, o `games_formated_release_data.csv` seria gerado com o retorno esperado para poder dar prosseguimento ao resto do projeto.

### **Como rodar o programa**

Baixe essa versão do programa por esse [link](https://drive.google.com/file/d/1g_CjBgLV99615nJmWG9Sk9P-6UPlbbs8/view?usp=sharing) (O arquivo está linkado para permitir o acesso de qualquer pessoa com email institucional, caso não consiga acessar o link, pode-se baixar a pasta [Steam_games](https://github.com/Joao-Roberto-ov/LEDA_SteamProject/tree/main/Steam_Games) que funcionará do mesmo jeito).
Depois baixe o banco de dados [aqui](https://www.kaggle.com/datasets/fronkongames/steam-games-dataset/data) e coloque o arquivo `games.csv` dentro da pasta [dados](https://github.com/Joao-Roberto-ov/LEDA_SteamProject/tree/main/Steam_Games/dados).

Após adicionar o banco de dados na pasta, só abrir o arquivo [Main.java](https://github.com/Joao-Roberto-ov/LEDA_SteamProject/blob/main/Steam_Games/src/Main.java) e executar.

O programa irá retornar uma mensagem quando todos os arquivos terminarem de ser gerados.



**Integrantes do grupo:** 

- João Roberto
- Lucas Emanuel Ramos
- Mateus de Jesus
