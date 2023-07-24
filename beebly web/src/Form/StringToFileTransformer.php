<?php

namespace App\Form;

use Symfony\Component\Form\DataTransformerInterface;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;

class StringToFileTransformer implements DataTransformerInterface
{
    private $targetDirectory;

    public function __construct(string $targetDirectory)
    {
        $this->targetDirectory = $targetDirectory;
    }

    public function transform($value): ?File
    {
        if ($value instanceof File) {
            return $value;
        }

        if ($value === null) {
            return null;
        }

        return new File($this->targetDirectory . '/' . $value);
    }

    public function reverseTransform($value): ?string
    {
        if ($value instanceof UploadedFile) {
            $fileName = uniqid() . '.' . $value->guessExtension();
            $value->move($this->targetDirectory, $fileName);
            return $fileName;
        }

        return $value;
    }
}
