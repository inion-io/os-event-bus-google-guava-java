package io.inion.os.messaging.eventbus;

import com.google.gson.JsonObject;
import io.inion.os.messaging.eventbus.google.EventBus;
import io.inion.os.messaging.eventbus.skills.SkCheckAndRegister;
import io.inion.os.messaging.eventbus.skills.SkPublish;
import io.inion.os.messaging.eventbus.skills.SkRegister;
import io.inion.os.messaging.eventbus.skills.SkUnregister;
import io.inion.os.common.SiCell;
import io.inion.os.common.annotation.Cell;
import io.inion.os.common.annotation.CellType;
import io.inion.os.common.exception.CellRunException;
import io.inion.os.common.messaging.SiEventMessage;
import io.inion.os.common.messaging.eventbus.SiEventBus;
import io.inion.os.messaging.eventbus.SiEventBusGoogleGuava.SiEventBusGoogleGuavaObject;

@CellType(
    objectClass = SiEventBusGoogleGuavaObject.class,
    type = SiEventBus.CELL_TYPE,
    uuid = SiEventBus.CELL_UUID
)
public interface SiEventBusGoogleGuava extends SiEventBus<SiEventBusGoogleGuava> {

  class SiEventBusGoogleGuavaObject extends
      SiCell.SiCellObject<SiEventBusGoogleGuava, JsonObject> implements
      SiEventBusGoogleGuava {

    private EventBus eventBus;
    @Cell
    private SkPublish publish;
    @Cell
    private SkRegister register;
    @Cell
    private SkUnregister unregister;
    @Cell(order = 10)
    private SkCheckAndRegister checkAndRegister;
    @Cell
    private SiEventBusCellAnalyzerGoogleGuava cellAnalyzer;

    @Override
    public void afterCreate() {
      eventBus = new EventBus();
    }

    @Override
    public void checkAndRegister(SiCell<?, ?> cell) {
      if (checkAndRegister == null) {
        return;
      }

      try {
        checkAndRegister
            .buildTransientInstance()
            .setEventBus(eventBus)
            .setCell(cell)
            .runAndDestroy();
      } catch (CellRunException e) {
        log().error(e);
      }
    }

    @Override
    public void publish(SiEventMessage<?> message) {
      try {
        publish
            .buildTransientInstance()
            .setEventBus(eventBus)
            .setMessage(message)
            .runAndDestroy();
      } catch (CellRunException e) {
        log().error(e);
      }
    }

    @Override
    public void register(SiCell<?, ?> listener) {
      try {
        register
            .buildTransientInstance()
            .setEventBus(eventBus)
            .setListener(listener)
            .runAndDestroy();
      } catch (CellRunException e) {
        log().error(e);
      }
    }

    @Override
    public void unregister(SiCell<?, ?> listener) {
      try {
        unregister
            .buildTransientInstance()
            .setEventBus(eventBus)
            .setListener(listener)
            .runAndDestroy();
      } catch (CellRunException e) {
        log().error(e);
      }
    }
  }
}
