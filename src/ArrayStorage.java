import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countOfResume = 0;

    void clear() {
        Arrays.fill(storage, null);
        countOfResume = 0;
    }

    void save(Resume r) {
        if (countOfResume <= storage.length) {
            storage[countOfResume++] = r;
        } else {
            System.out.println("Список резюме переполнен!");
        }
    }

    Resume get(String uuid) {
        int res = findIndex(uuid);
        if (res >= 0) {
            return storage[res];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        if (countOfResume > 0) {
            int res = findIndex(uuid);
            if (res < countOfResume - 1) {
                System.arraycopy(storage, (res + 1), storage, res, (countOfResume - res - 1));
            }
            countOfResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[countOfResume];
        System.arraycopy(storage, 0, res, 0, countOfResume);
        return res;
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
