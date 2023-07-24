<?php

// src/Service/TwigExtension.php

namespace App\Service;

use Twig\Extension\AbstractExtension;
use Twig\TwigFilter;

class TwigExtension extends AbstractExtension
{
    public function getFilters(): array
    {
        return [
            new TwigFilter('query_string', [$this, 'queryStringFilter']),
        ];
    }

    public function queryStringFilter($params): string
    {
        return http_build_query($params);
    }
}
