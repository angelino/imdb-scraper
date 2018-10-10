# TASKS - 10/10/2018

- Definir tempo
- Revisar material

## Fundamentos & Conceitos

* Config. plugin
    - Alt+Shift+P
    - Parinfer
    
* Review collections

* Gestão de libs
    * project.clj
    * namespaces
    * require/import (ns/repl)
    
* Funções
    * Criação/Chamada defn vs. def (fn ...)
    * Parametros
    * Local scopes let
    * varargs e parametros opcionais
    
* Compreensão de listas
    (for [bindings] body)
    
* defonce
* first/rest/last*
* take
* clojure.pprint / clojure.inspector

* map/filter 
    
* Java interop
    * import
    * chamada de metodos em instancias
    * metodos estaticos
    * Construtores (new Type args) / (Type. args)
    
    ref.: https://clojure.org/guides/learn/functions#_java_interop

## Exercícios

>1. Adicione a lib JSoup (https://jsoup.org/) no projeto e importe no namespace imdb-scraper.core e experimente a biblioteca. 15min

2. Crie uma funcao get-page que receba como parâmetro uma url e faça o download da pagina utilizando o JSoup. 10min

3. Adicione um novo parametro opcional opts que receba um mapa com as chaves :timeout e :max-body-size para
a configuração da conexão antes de invocar o método get. 5min

4. Armazene o resultado da função get-page em um var page no namespace imdb-scraper.core. 5min

5. Armazene a tabela em um var no namespace e as Armazene as linhas da tabela em outro var 5min

6. Recupere somente o texto das colunas de cada linha da tabela. 10min

>7. Utilize os namespace clojure.pprint e clojure.inspector para inspecionar os dados 5min   

>8. Para cada linha/coluna (record) da tabela crie um mapa com as chaves :ranking <Integer>, :title <String>, :published <Integer> 
e :rating <Double>. 15min
Não esqueça de fazer os tratamentos adequados para os dados
Hints.: Utilize a função clojure.string/split para separar os dados
        Utilize a função clojure.string/escape para remover caracteres indesejados 

-- Pizza TIME!!!! (15min)

Have fun!!!!

9. Quais filmes tiveram uma avaliação igual ou superior a 9? 5min 

10. Quais filmes foram publicados em 2018? 5min

11. Quantos filmes do Piratas do Caribe estão na lista? 5min

12. Imprima uma tabela formatada da lista de filmes utilizando clojure.pprint. 5min

-- That's all folks!!!


## Q.A & Feedback (15min)


## Desafios

1. Qual o filme mais antigo encontrado na lista? 
(sort+take??)

2. Existe uma forma de encadear chamadas a funções ou métodos chamado Thread Macro. Pesquise sobre e refatore a função
get-page para utilizá-lo:
;(-> url
;    Jsoup/connect
;    (.timeout 60000)
;    (.maxBodySize (* 10 1024 1024))
;    .get))


