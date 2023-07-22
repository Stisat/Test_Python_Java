package TestPythonJava.Java;


import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class ToyShop {
    static File logFile = null;
    static FileWriter fileWriter = null;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        /*
          Список игрушек в магазине
         */
        String[] toydata = {
                "1;Робот;10;5",
                "2;Плюшевый мишка;15;25",
                "3;Конструктор;5;20",
                "4;Машинка;20;35",
                "5;Кукла;10;15"};

        ArrayList<Toys> listToys = putToys(toydata);



        System.out.println("Добро пожаловать!");
        boolean check = true;
        while(check) {
            System.out.println("###########################################################");
            System.out.println("Структура приложения:");
            System.out.println("1 - 'Отобразить игрушки в магазине';");
            System.out.println("2 - 'Произвести розыгрыш';");
            System.out.println("0 - Выход из приложения.");
            System.out.println("###########################################################");
            System.out.print("Ваш выбор: ");
            int task = Integer.parseInt(scan.nextLine());
            switch (task) {
                case 1:
                    printToys(listToys);
                    break;
                case 2:
                    Queue<Toys> getT= getToys(listToys);
                    winToy(getT,listToys);
                    addFile(getT);
                    break;
                case 0:
                    System.out.println("Завершение работы приложения.");
                    check = false;
                    break;
                default:
                    System.out.println("Вы указали некорректный номер действия.\n Повторите попытку.");
                    break;
            }
        }



    }

    /**
     * функция Put для занесения игрушек в массив
     */
    static ArrayList<Toys> putToys(String[] toydata){
        ArrayList<Toys>  toylist = new ArrayList<>();
        for (String elem:toydata){
            String[] toyParts = elem.split(";");
            int id = Integer.parseInt(toyParts[0]);
            String name = toyParts[1];
            int count = Integer.parseInt(toyParts[2]);
            int weight = Integer.parseInt(toyParts[3]);
            toylist.add(new Toys(id,name,count,weight));

        }
        return toylist;
    }

    /**
     * функция создания очереди игрушек на выдачу
     */
    static Queue<Toys> getToys(ArrayList<Toys> data){
        Random random = new Random();
        Queue<Toys> priotityQueue = new PriorityQueue<>(Comparator.comparingInt(Toys::getWeight));
        priotityQueue.addAll(data);
        ArrayList<Integer> numCh = new ArrayList<>();
        for (Toys items: data) {
            numCh.add(items.getWeight());
        }
        int maxW = Collections.max(numCh);
        int r = random.nextInt(1,maxW+1);
            for (Toys elem: data) {
                if (r < elem.getWeight() + 1) {
                    priotityQueue.add(elem);
                }
            }
        return priotityQueue;
    }

    /**
     * функция удаления игрушек из основного массива по Стаку
     */
    static ArrayList<Toys> winToy(Queue<Toys> data, ArrayList<Toys> toysList){
        for (Toys items:data) {
            System.out.printf("id: %d; name:%s;\n",items.getId(),items.getToysName());
        }
       Iterator<Toys> iterator = data.iterator();
       while (iterator.hasNext()){
           Toys temp = data.remove();
           for (Toys elem: toysList) {
               if(temp.equals(elem)){
                   if(elem.getCount()>0){
                       elem.setCount(elem.getCount()-1);

                   }
                   else  {
                       toysList.remove(elem);
                       return toysList;
                   }

               }

           }

       }
       return toysList;
    }

    static void addFile(Queue<Toys> arr){
        try {
            logFile = new File("ToysWin.txt");
            fileWriter = new FileWriter(logFile,true);
            try {
                for (Toys item : arr) {
                    fileWriter.append(String.format("id:%d;name:%s\n",item.getId(),item.getToysName()));
                }
                fileWriter.flush();
                fileWriter.close();
            }
            catch (Exception ex) {
                System.out.println("Произошла ошибка записи в файл.");
            }
        }
        catch(Exception ex){
            System.out.println("Произошла ошибка.");
        }
    }
    static void printToys(ArrayList<Toys> data){
        for (Toys element: data) {
            System.out.printf("id: %d; name:%s; count:%d\n",element.getId(),element.getToysName(),element.getCount());
        }
    }





}











