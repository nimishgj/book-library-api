package dev.infraspec;

import java.util.List;

public class ExitOption  implements Option{
    @Override
    public void execute(List<Book> books) {
        System.exit(0);
    }
}
