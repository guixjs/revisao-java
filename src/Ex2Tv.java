import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ex2Tv {

  static Scanner sc = new Scanner(System.in);

  static Map<Integer, List<String>> canais = Map.of(
      1, List.of("Sbt", "RATINHO", "Chaves", "Alguma novela mexicana"),
      2, List.of("Globo", "Lagoa azul", "Jornal Nacional"),
      3, List.of("SporTV", "Sport perdendo", "Verstapen Ganhando"),
      4, List.of("Discovery Channel", "Documentário: Os pássaros mais raros do sudeste mineiro",
          "Documentário: A Terra (narrado com voz estranha)"));

  static Map<Integer, List<String>> jogos = Map.of(
      1, List.of("The Last of Us 2", "LEVANTA JOEL!!!", "Não dá pra pular do penhasco"),
      2, List.of("Death Stranding 2", "Você não está entendendo nada", "??????"),
      3, List.of("Hollow knight", "Você se perdeu"),
      4, List.of("Red Dead Redemption 2", "Dutch tem um plano", "\"Sister,... I'm afraid\"", "Você está chorando"),
      5, List.of("GTA VI", "ERROR", "OLHA ESSA GEOMETRIA :O"));

  static Map<Integer, List<String>> series = Map.of(
      1, List.of("Breaking bad", "\"Say my name.\"", "Melhor série já feita"),
      2, List.of("Kpop Demon hunters", "Tem gente que gosta"),
      3, List.of("Algum anime", "Tá em japonês", "Arigato saionara"));

  static int volume = 20;
  static boolean ligada = false;
  static boolean consoleLigado = false;
  static boolean mudo;
  static int canalEscolhido = 1;
  static int jogoEscolhido = 1;
  static int serieEscolhida = 1;

  public static void main(String[] args) {

    System.out.println("== VOCÊ SENTOU NO SOFÁ ==");
    ligada = menuInicial();

    int escolha;

    while (ligada) {
      String mensagemMudo = mudo ? "Ativado" : "Desativado";
      String mensagem = consoleLigado ? "Jogar" : "Ligar console";
      System.out.printf("""
          INFORMAÇÕES:
            -------------------------
            Volume atual: %d
            Mudo: %s
            -------------------------
            """, volume, mensagemMudo);
      System.out.println();
      System.out.println("==== TV - MENU INCIAL ====");
      System.out.printf("""
          [1] - Assistir Tv Aberta
          [2] - %s
          [3] - Assistir Netflix
          [0] - Levantar do sofá
          """, mensagem);

      System.out.print("Escolha a opção que deseja fazer: ");
      escolha = sc.nextInt();
      System.out.println("================================");

      switch (escolha) {
        case 1:
          assistirTvAberta();
          break;
        case 2:
          if (consoleLigado) {
            menuConsole();
          } else {
            System.out.println("Console ligado!");
            consoleLigado = true;
          }
          break;
        case 3:
          menuNetflix();
          break;
        case 0:
          System.out.println("Levantando do sofá...");
          ligada = false;
          break;

        default:
          System.out.println("Opção inválida");
          break;
      }
    }

  }

  static boolean menuInicial() {
    int opcao;

    do {
      String mensagem = consoleLigado ? "Desligar console" : "Ligar console";
      System.out.println();
      System.out.println("==== TV - MENU DE OPÇÕES ====");
      System.out.printf("""
          [1] - Ligar a TV
          [2] - %s
          [0] - Levantar do sofá
          """, mensagem);

      System.out.print("Escolha a opção que deseja fazer: ");
      opcao = sc.nextInt();
      System.out.println("================================");

      if (opcao == 1) {
        System.out.println("Tv ligada");
        return true;
      } else if (opcao == 2) {
        if (consoleLigado) {
          System.out.println("Console desligado");
          consoleLigado = false;
        } else {
          System.out.println("Console ligado");
          consoleLigado = true;
        }
      } else if (opcao == 0) {
        System.out.println("Levantando do sofá...");
      } else {
        System.out.println("Opção inválida!");
      }
    } while (opcao != 1 && opcao != 0);
    return false;
  }

  // funções relacionadas a TV ABERTA

  static void assistirTvAberta() {
    int escolha;

    List<String> infoCanal = informacoesCanal();
    String nomeCanal;
    String programacao = "";
    int i = 1;
    do {
      nomeCanal = infoCanal.get(0);

      if (i < infoCanal.size()) {
        programacao = infoCanal.get(i);
        i++;
      } else {
        i = 1;
        programacao = infoCanal.get(i);
        i++;
      }

      String mensagem = consoleLigado ? "Jogar" : "Ligar console";
      String mensagemMudo = mudo ? "Ativado" : "Desativado";

      System.out.printf("""
          INFORMAÇÕES:
            -------------------------
            Você está assistindo o canal: %s
            Programação: %s
            Volume atual: %d
            Mudo: %s
            -------------------------
            """, nomeCanal, programacao, volume, mensagemMudo);
      System.out.println();
      System.out.println("==== TV - MENU CANAIS ABERTOS ====");
      System.out.printf("""
          [1] - Pesquisar canal
          [2] - Mudar canal
          [3] - Aumentar volume
          [4] - Diminuir volume
          [5] - Mudo
          [6] - %s
          [7] - Netflix
          [0] - Levantar do sofá
          """, mensagem);
      System.out.print("Escolha a opção que deseja fazer: ");
      escolha = sc.nextInt();
      System.out.println("================================");

      switch (escolha) {
        case 1:
          canalEscolhido = escolherCanal();
          if (canalEscolhido == 0) {
            canalEscolhido = 1;
          }
          infoCanal = informacoesCanal();
          break;
        case 2:
          canalEscolhido++;
          infoCanal = informacoesCanal();
          break;
        case 3:
          volume = aumentarVolume();
          break;
        case 4:
          volume = diminuirVolume();
          break;
        case 5:
          mudo = mudo();
          break;
        case 6:
          if (!consoleLigado) {
            System.out.println("Console ligado!");
            consoleLigado = true;
          }
          menuConsole();
          break;
        case 7:
          menuNetflix();
          break;
        case 0:
          ligada = false;
          System.out.println("Levantando do sofá...");
          break;

        default:
          break;
      }

    } while (escolha != 0);

  }

  static int escolherCanal() {
    boolean invalido = true;
    int escolha;
    while (invalido) {
      System.out.println("""
          === CANAIS DISPONÍVEIS ===
          [1] - Sbt
          [2] - Globo
          [3] - SportTV
          [4] - Discovery Channel
          [0] - Cancelar Pesquisa
          """);
      System.out.print("Digite o número do canal que deseja escolher:");
      escolha = sc.nextInt();

      switch (escolha) {
        case 1:
          invalido = false;
          return 1;
        case 2:
          invalido = false;
          return 2;
        case 3:
          invalido = false;
          return 3;
        case 4:
          invalido = false;
          return 4;
        case 0:
          System.out.println("Pesquisa cancelada");
          invalido = false;
        default:
          System.out.println("Opção inválida");
          break;
      }
    }
    return 0;
  }

  static List<String> informacoesCanal() {
    if (canais.containsKey(canalEscolhido)) {
      return canais.get(canalEscolhido);
    } else {
      return null;
    }
  }

  // funções relacionadas ao CONSOLE

  static void menuConsole() {
    int escolha = 0;

    List<String> infoJogo = informacoesJogo();
    String nomeJogo;
    String mensagemJogo = "";
    int i = 1;
    do {

      String mensagemMudo = mudo ? "Ativado" : "Desativado";

      nomeJogo = infoJogo.get(0);

      if (i < infoJogo.size()) {
        mensagemJogo = infoJogo.get(i);
        i++;
      } else {
        i = 1;
        mensagemJogo = infoJogo.get(i);
        i++;
      }
      System.out.printf("""
          INFORMAÇÕES:
            -------------------------
            Você está jogando: %s
            %s
            Volume atual: %d
            Mudo: %s
            -------------------------
              """, nomeJogo, mensagemJogo, volume, mensagemMudo);
      System.out.println();
      System.out.println("==== TV - MENU CONSOLE ====");
      System.out.printf("""
          [1] - Escolher jogo
          [2] - Continuar jogando
          [3] - Aumentar volume
          [4] - Diminuir volume
          [5] - Mudo
          [0] - Parar de jogar
          """);
      System.out.print("Escolha a opção que deseja fazer: ");
      escolha = sc.nextInt();
      System.out.println("================================");

      switch (escolha) {
        case 1:
          jogoEscolhido = escolherJogo();
          if (jogoEscolhido == 0) {
            jogoEscolhido = 1;
          }
          infoJogo = informacoesJogo();
          break;
        case 2:
          break;
        case 3:
          volume = aumentarVolume();
          break;
        case 4:
          volume = diminuirVolume();
          break;
        case 5:
          mudo = mudo();
          break;
        case 0:
          break;
        default:
          break;
      }

    } while (escolha != 0);

  }

  static int escolherJogo() {
    boolean invalido = true;
    int escolha;
    while (invalido) {
      System.out.println("""
          === JOGOS DISPONÍVEIS ===
          [1] - The Last of Us 2
          [2] - Death Stranding 2
          [3] - Hollow knight
          [4] - Red Dead Redemption 2
          [5] - VI ?
          [0] - Cancelar Pesquisa
          """);
      System.out.print("Digite o número do jogo que deseja escolher:");
      escolha = sc.nextInt();

      switch (escolha) {
        case 1:
          invalido = false;
          return 1;
        case 2:
          invalido = false;
          return 2;
        case 3:
          invalido = false;
          return 3;
        case 4:
          invalido = false;
          return 4;
        case 5:
          invalido = false;
          return 5;
        case 0:
          System.out.println("Pesquisa cancelada");
          invalido = false;
        default:
          System.out.println("Opção inválida");
          break;
      }
    }
    return 0;
  }

  static List<String> informacoesJogo() {
    if (jogos.containsKey(jogoEscolhido)) {
      return jogos.get(jogoEscolhido);
    } else {
      return null;
    }
  }

  // funções relacionadas a NETFLIX

  static void menuNetflix() {
    int escolha = 0;

    List<String> infoSerie = informacoesSerie();
    String nomeSerie;
    String mensagemSerie = "";
    int i = 1;
    do {

      String mensagemMudo = mudo ? "Ativado" : "Desativado";
      String mensagem = consoleLigado ? "Jogar" : "Ligar console";

      nomeSerie = infoSerie.get(0);

      if (i < infoSerie.size()) {
        mensagemSerie = infoSerie.get(i);
        i++;
      } else {
        i = 1;
        mensagemSerie = infoSerie.get(i);
        i++;
      }
      System.out.printf("""
          INFORMAÇÕES:
            -------------------------
            Você está assitindo: %s
            %s
            Volume atual: %d
            Mudo: %s
            -------------------------
              """, nomeSerie, mensagemSerie, volume, mensagemMudo);
      System.out.println();
      System.out.println("==== TV - MENU NETFLIX ====");
      System.out.printf("""
          [1] - Escolher serie
          [2] - Continuar assistindo
          [3] - Aumentar volume
          [4] - Diminuir volume
          [5] - Mudo
          [6] - %s
          [0] - Assistir TvAberta
          """, mensagem);
      System.out.print("Escolha a opção que deseja fazer: ");
      escolha = sc.nextInt();
      System.out.println("================================");

      switch (escolha) {
        case 1:
          serieEscolhida = escolherSerie();
          if (serieEscolhida == 0) {
            serieEscolhida = 1;
          }
          infoSerie = informacoesSerie();
          break;
        case 2:
          break;
        case 3:
          volume = aumentarVolume();
          break;
        case 4:
          volume = diminuirVolume();
          break;
        case 5:
          mudo = mudo();
          break;
        case 6:
          if (consoleLigado) {
            menuConsole();
          } else {
            System.out.println("Console ligado!");
            consoleLigado = true;
          }
          break;
        case 0:
          break;
        default:
          break;
      }

    } while (escolha != 0);
  }

  static List<String> informacoesSerie() {
    if (series.containsKey(serieEscolhida)) {
      return series.get(serieEscolhida);
    } else {
      return null;
    }
  }

  static int escolherSerie() {
    boolean invalido = true;
    int escolha;
    while (invalido) {

      System.out.println("""
          === SÉRIES DISPONÍVEIS ===
          [1] - Breaking bad
          [2] - Kpop Demon hunters
          [3] - Algum anime
          [0] - Cancelar Pesquisa
          """);
      System.out.print("Digite o número da série que deseja escolher:");
      escolha = sc.nextInt();

      switch (escolha) {
        case 1:
          invalido = false;
          return 1;
        case 2:
          invalido = false;
          return 2;
        case 3:
          invalido = false;
          return 3;
        case 0:
          System.out.println("Pesquisa cancelada");
          invalido = false;
        default:
          System.out.println("Opção inválida");
          break;
      }
    }
    return 0;
  }

  // auxiliares

  static int aumentarVolume() {
    if (volume < 100) {
      volume += 5;
    } else {
      System.out.println("Volume máximo!");
    }
    return volume;
  }

  static int diminuirVolume() {
    if (volume > 0) {
      volume -= 5;
    } else {
      System.out.println("Volume mínimo!");
    }
    return volume;
  }

  static boolean mudo() {
    mudo = !mudo;
    System.out.println("Mudo " + (mudo ? "ativado" : "desativado"));
    return mudo;
  }

}
