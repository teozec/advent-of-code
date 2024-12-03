using System.Text.RegularExpressions;

namespace AdventOfCode2023
{
    internal class Day2
    {
        private class Configuration
        {
            public int Red { get; init; }
            public int Blue { get; init; }
            public int Green { get; init; }

            public int Power { get => Red * Blue * Green; }
            public bool CanBeExtractedFrom(Configuration configuration)
                => Red <= configuration.Red && Blue <= configuration.Blue && Green <= configuration.Green;
        }

        private class Game
        {
            private readonly IEnumerable<Configuration> sets;
            public int Id { get; }

            public Configuration MinimumConfiguration
            {
                get => new()
                {
                    Red = sets.Select(s => s.Red).Max(),
                    Blue = sets.Select(s => s.Blue).Max(),
                    Green = sets.Select(s => s.Green).Max(),
                };
            }

            public Game(string description)
            {
                Regex regex = new("Game (?<id>[0-9]+):(?<games>.*)");
                Match match = regex.Match(description);

                Id = int.Parse(match.Groups["id"].Value);
                sets = GetSets(match.Groups["games"].Value.Split(';'));
            }

            private static IEnumerable<Configuration> GetSets(string[] sets)
            {
                Regex regex = new("(?<qty>\\d+) (?<color>red|green|blue)");
                foreach (var set in sets)
                {
                    int red = 0, blue = 0, green = 0;

                    var matches = regex.Matches(set);
                    foreach (Match match in matches)
                    {
                        int qty = int.Parse(match.Groups["qty"].Value);
                        switch (match.Groups["color"].Value)
                        {
                            case "red": red = qty; break;
                            case "blue": blue = qty; break;
                            case "green": green = qty; break;
                        }
                    }
                    yield return new() { Red = red, Blue = blue, Green = green };
                }
            }
        }

        public static int Exercise1()
            => File.ReadLines("Inputs/Day2.txt")
                .Select(line => new Game(line))
                .Where(game =>
                    game.MinimumConfiguration.CanBeExtractedFrom(
                        new() { Red = 12, Blue = 14, Green = 13 }))
                .Sum(game => game.Id);

        public static int Exercise2()
            => File.ReadLines("Inputs/Day2.txt")
                .Select(line => new Game(line))
                .Sum(game => game.MinimumConfiguration.Power);

    }

}
