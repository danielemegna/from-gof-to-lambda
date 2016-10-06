package org.mfusco.fromgoftolambda.talk.chainofresponsibility;

public class ChainOfRespGoF {

    public interface FileParser {
        String parse(File file);

        void setNextParser(FileParser next);
    }

    public abstract static class AbstractFileParser implements FileParser {
        protected FileParser next;

        @Override
        public void setNextParser(FileParser next) {
            this.next = next;
        }
    }

    public static class TextFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.TEXT) {
                return "Text file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static class PresentationFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.PRESENTATION) {
                return "Presentation file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static class AudioFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.AUDIO) {
                return "Audio file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static class VideoFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.VIDEO) {
                return "Video file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static void main(String[] args) {
        FileParser textParser = new TextFileParser();
        FileParser presentationParser = new PresentationFileParser();
        FileParser audioParser = new AudioFileParser();
        FileParser videoParser = new VideoFileParser();

        textParser.setNextParser(presentationParser);
        presentationParser.setNextParser(audioParser);
        audioParser.setNextParser(videoParser);

        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");
        System.out.println(textParser.parse(file));

        file = new File(File.Type.VIDEO, "A cool video file!");
        System.out.println(textParser.parse(file));

        file = new File(File.Type.PRESENTATION, "A cool presentation file!");
        System.out.println(textParser.parse(file));

        file = new File(File.Type.TEXT, "A cool text file!");
        System.out.println(textParser.parse(file));
    }
}
