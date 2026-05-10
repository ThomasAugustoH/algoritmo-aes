# algoritmo-aes
Integrantes: Daniel Iensen Neves, Gabriel Ribas Cestari, Thomas Augusto Haskel

# Desenvolvimento
* Java 22.0.2
* Gradle 9.5.0
    * Para Windows:
    * Baixar https://services.gradle.org/distributions/gradle-9.5.0-bin.zip
    * Descompactar em `C:\Gradle`
    * Adicionar `C:\Gradle\gradle-9.5.0\bin` ao Path das variáveis de ambiente

## Executar
- Executar comandos abaixo utilizando uma janela de comando powershell
    * ``.\gradlew.bat clean build``
    * ``.\gradlew.bat run``

- Caso estiver executando via bash utilizar os comandos abaixo
    * ``./gradlew.bat clean build``
    * ``./gradlew.bat run``

## TODO:
* Reestruturar a classe AES, talvez criar uma classe Criptografia para centralizar os metodos de cifrar/decifrar e as classes ECB e CBC
* Todo o codigo
* Talvez fazer testes unitarios
