package io.inion.os.messaging.eventbus.skills;

import io.inion.os.messaging.eventbus.google.EventBus;
import io.inion.os.common.SiCell;
import io.inion.os.common.annotation.CellType;
import io.inion.os.common.exception.CellRunException;
import io.inion.os.common.messaging.SiEventMessage;
import io.inion.os.messaging.eventbus.skills.SkPublish.SkPublishObject;

@CellType(
    objectClass = SkPublishObject.class,
    type = SkPublish.CELL_TYPE,
    uuid = SkPublish.CELL_UUID
)
public interface SkPublish extends SiCell<SkPublish, Void> {

  String CELL_TYPE = "event-bus-publish-skill";

  String CELL_UUID = "4FAE8365-9642-48FF-9BE1-8AE16CF5D773";

  SkPublish setEventBus(EventBus eventBus);

  SkPublish setMessage(SiEventMessage<?> message);

  class SkPublishObject extends SiCellObject<SkPublish, Void> implements SkPublish {

    private EventBus eventBus;
    private SiEventMessage<?> message;

    @Override
    public SkPublish run() throws CellRunException {
      checkRunValuesForNull(eventBus, message);

      eventBus.post(message);

      return getSelf();
    }

    @Override
    public SkPublish setEventBus(EventBus eventBus) {
      this.eventBus = eventBus;
      return getSelf();
    }

    @Override
    public SkPublish setMessage(SiEventMessage<?> message) {
      this.message = message;
      return getSelf();
    }
  }
}
