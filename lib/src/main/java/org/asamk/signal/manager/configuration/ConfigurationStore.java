package org.asamk.signal.manager.configuration;

public class ConfigurationStore {

    private final Saver saver;

    private Boolean readReceipts;
    private Boolean unidentifiedDeliveryIndicators;
    private Boolean typingIndicators;
    private Boolean linkPreviews;

    public ConfigurationStore(final Saver saver) {
        this.saver = saver;
    }

    public static ConfigurationStore fromStorage(Storage storage, Saver saver) {
        final var store = new ConfigurationStore(saver);
        store.readReceipts = storage.readReceipts;
        store.unidentifiedDeliveryIndicators = storage.unidentifiedDeliveryIndicators;
        store.typingIndicators = storage.typingIndicators;
        store.linkPreviews = storage.linkPreviews;
        return store;
    }

    public Boolean getReadReceipts() {
        return readReceipts;
    }

    public void setReadReceipts(final boolean readReceipts) {
        this.readReceipts = readReceipts;
        saver.save(toStorage());
    }

    public Boolean getUnidentifiedDeliveryIndicators() {
        return unidentifiedDeliveryIndicators;
    }

    public void setUnidentifiedDeliveryIndicators(final boolean unidentifiedDeliveryIndicators) {
        this.unidentifiedDeliveryIndicators = unidentifiedDeliveryIndicators;
        saver.save(toStorage());
    }

    public Boolean getTypingIndicators() {
        return typingIndicators;
    }

    public void setTypingIndicators(final boolean typingIndicators) {
        this.typingIndicators = typingIndicators;
        saver.save(toStorage());
    }

    public Boolean getLinkPreviews() {
        return linkPreviews;
    }

    public void setLinkPreviews(final boolean linkPreviews) {
        this.linkPreviews = linkPreviews;
        saver.save(toStorage());
    }

    private Storage toStorage() {
        return new Storage(readReceipts, unidentifiedDeliveryIndicators, typingIndicators, linkPreviews);
    }

    public record Storage(
            Boolean readReceipts, Boolean unidentifiedDeliveryIndicators, Boolean typingIndicators, Boolean linkPreviews
    ) {}

    public interface Saver {

        void save(Storage storage);
    }
}
