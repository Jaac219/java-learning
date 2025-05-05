package snacks_machine.service;

import snacks_machine.domain.Snack;
import java.util.List;

public interface ISnacksService {
    void addSnack(Snack snack);
    List<Snack> getSnacks();
    void showSnacks();
}
