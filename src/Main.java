import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Thread firstThread = new Translator();
        Thread secondThread = new Minimum();
        firstThread.start();
        secondThread.start();

    }

    public static final ArrayList<Integer> list = new ArrayList<>();
    public static boolean flag = false;


    public static class Translator extends Thread{

        @Override
        public void run() {

            Map<String, Integer> numeralsMap = new HashMap<>();

            numeralsMap.put("one", 1);
            numeralsMap.put("two", 2);
            numeralsMap.put("three", 3);
            numeralsMap.put("four", 4);
            numeralsMap.put("five", 5);
            numeralsMap.put("six", 6);
            numeralsMap.put("seven", 7);
            numeralsMap.put("eight", 8);
            numeralsMap.put("nine", 9);
            numeralsMap.put("ten", 10);
            numeralsMap.put("eleven", 11);
            numeralsMap.put("twelve", 12);
            numeralsMap.put("thirteen", 13);
            numeralsMap.put("fourteen", 14);
            numeralsMap.put("fifteen", 15);
            numeralsMap.put("sixteen", 16);
            numeralsMap.put("seventeen", 17);
            numeralsMap.put("eighteen", 18);
            numeralsMap.put("nineteen", 19);
            numeralsMap.put("twenty", 20);
            numeralsMap.put("thirty", 30);
            numeralsMap.put("forty", 40);
            numeralsMap.put("fifty", 50);
            numeralsMap.put("sixty", 60);
            numeralsMap.put("seventy", 70);
            numeralsMap.put("eighty", 80);
            numeralsMap.put("ninety", 90);
            for (; ; ) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String s = null;
                try {
                    s = reader.readLine();
                    if (s.equals("exit")) throw new InterruptedException();
                    if (s.equals("")) continue;
                }
                catch (InterruptedException e) {
                    flag = true;
                    break;
                }
                catch (IOException e){
                    e.getStackTrace();
                }


                String[] numbers = s.split("\\s");
                ArrayList<Integer> count = new ArrayList<>();
                for (int i = 0; i < numbers.length; i++) {
                    for (Map.Entry<String, Integer> map : numeralsMap.entrySet()) {
                        if (numbers[i].equals(map.getKey())) {
                            count.add(map.getValue());
                            break;
                        }
                    }
                    if (numbers[i].equals("thousand"))
                        count.set(count.size() - 1, (count.get(count.size() - 1) * 1000));
                    if (numbers[i].equals("hundred")) count.set(count.size() - 1, (count.get(count.size() - 1) * 100));
                }
                if (count.size() == 0) continue;

                int temp = 0;
                for (int i = 0; i < count.size(); i++) {
                    temp += count.get(i);
                }
                synchronized (list) { list.add(temp);}
            }
        }

    }

    public static class Minimum extends Thread{

        @Override
        public void run() {

        while (!flag) {
            try {
                Thread.sleep(5000);
                if(list.size()>0) System.out.println(findMin(list));
                else System.out.println("Nothing to remove");
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }

        }

        public int findMin(ArrayList <Integer> list){
            int min = list.get(0);
            int index = 0;

            for (int i = 0; i < list.size() ; i++) {
                if(list.get(i) < min) {
                    min = list.get(i);
                    index = i;
                }
            }
            list.remove(index);
            return min;
        }
    }

}


