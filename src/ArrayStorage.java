import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int countOfResume = 0;

    void clear() {
        Arrays.fill(storage, null);
        countOfResume = 0;
    }

    void update(Resume r, Resume newR) {
        int index = findIndex(r.uuid);
        if (index >= 0) {
            storage[index] = newR;
        } else {
            System.out.println("Такого резюме нет в базе!");
        }

    }

    void save(Resume r) {
        if (countOfResume <= storage.length) {
            if (findIndex(r.uuid) < 0) {
                storage[countOfResume++] = r;
            } else {
                System.out.println("Такое резюме уже есть!");
            }
        } else {
            System.out.println("Список резюме переполнен!");
        }
    }

    Resume get(String uuid) {
        int res = findIndex(uuid);
        if (res < 0) System.out.println("Резюме не найдено!");
        return res >= 0 ? storage[res] : null;
    }

    void delete(String uuid) {
        int res;
        res = findIndex(uuid);
        if (res >= 0) {     //
            if (res < countOfResume - 1) storage[res] = storage[countOfResume - 1];
            storage[countOfResume - 1] = null;
            countOfResume--;
        } else {
            System.out.println("Такого резюме не существует!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countOfResume);
    }

    int size() {
        return countOfResume;
    }

    private int findIndex(String f) {
        for (int i = 0; i < countOfResume; i++) {
            if (storage[i].uuid.equals(f)) {
                return i;
            }
        }
        return -1;
    }

}
