import java.io.*;
import java.util.*;

public class DataReader {
    public Dataset readDataset() {
        File dir = new File(System.getProperty("user.dir"));
        System.out.println("Current directory: " + dir.getAbsolutePath());
        System.out.println("Please choose your dataset file: ");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("ERROR: No file under current directory.");
            return null;
        }
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (File file : files) {
            // if file is a directory, ignore it.
            if (file.isDirectory()) {
                continue;
            }
            map.put(map.size() + 1, file.getAbsolutePath());
            System.out.println(String.valueOf(map.size()) + ": " + file.getName());
        }

        Scanner scanner = new Scanner(System.in);
        String filePath = map.get(scanner.nextInt());
        Dataset dataset = new Dataset();
        Set<Point> positivePoints = new HashSet<Point>();
        Set<Point> negativePoints = new HashSet<Point>();
        try {
            // coding method : ascii
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "ascii"));
            String line = reader.readLine();
            String[] words = line.split(" ");
            int dimensionality = Integer.valueOf(words[1]);
            dataset.setDimensionality(dimensionality);
            while ((line = reader.readLine()) != null) {
                words = line.split(" ");
                double[] coordinates = new double[dimensionality];
                for (int i = 0; i < dimensionality; i++) {
                    coordinates[i] = Double.valueOf(words[i]);
                }
                if (Integer.valueOf(words[words.length - 1]) == 1) {
                    positivePoints.add(new Point(coordinates, true));
                } else {
                    negativePoints.add(new Point(coordinates, false));
                }
            }
            dataset.setPositivePoints(positivePoints);
            dataset.setNegativePoints(negativePoints);
            reader.close();
        } catch (IOException e) {
            System.out.println("ERROR: can not read file " + filePath);
            return null;
        }
        return dataset;
    }
}
